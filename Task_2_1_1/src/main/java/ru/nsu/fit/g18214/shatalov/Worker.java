package ru.nsu.fit.g18214.shatalov;

import static java.lang.Thread.sleep;
import static ru.nsu.fit.g18214.shatalov.PizzaTime.orders;
import static ru.nsu.fit.g18214.shatalov.PizzaTime.warehouse;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

  //private final BlockingQueue<Order> takenOrdersQueue;
  private final int efficiency;
  private boolean busy;
  private boolean ready = false;
  private Order order;
  private final int name;

  /**
   * Creates worker with given parameters.
   * Calculates worker efficiency.
   * @param exp - given worker experience.
   * @param id - given worker id.
   */
  public Worker(int exp, int id) {
    //this.takenOrdersQueue = orders; //PizzaTime.java blocking queue.
    this.efficiency = (exp ^ 2) / 2;
    this.busy = false;
    this.name = id;
  }

  public boolean isBusy() {
    return this.busy;
  }

  public int getEfficiency() {
    return this.efficiency;
  }

  /**
   * Takes order from queue.
   * If there is no orders - waits 5 seconds and then returns null.
   * @throws InterruptedException if can't access queue.
   */
  public void takeOrder() throws InterruptedException {
    this.busy = true;
    this.order = orders.poll(5000, TimeUnit.MILLISECONDS); //Orders - defined in PizzaTime.java queue.
  }

  public void orderReady() {
    this.ready = true;
  }

  public void free() {
    this.busy = false;
    this.ready = false;
  }

  public int getName() {
    return this.name;
  }

  /**
   * Starts thread.
   * If PizzaTime shop is still working (PizzaTime.stop != true) ->
   * If button isn't pushed (other worker isn't waiting for new order to take) ->
   * -> push button and take latest order from orders queue (if there is no order - wait for it 5 sec).
   * If after 5 seconds there is still no order -> resets all states and thread starts over.
   * Worker become busy (can't take orders).
   * Thread wait a certain time based on worker efficiency.
   * After this time order counts as ready and worker place it in warehouse queue.
   * If warehouse is full - worker wait.
   * When order placed in warehouse, workers state become free ->
   * -> (ready for orders) and thead restarts.
   */
  public void run() {
    try {
      if (!PizzaTime.stop) {
        if (!isBusy() && !PizzaTime.buttonW) {
          PizzaTime.buttonW = true;
          System.out.println("Worker " + this.name + " pushed button");
          if (PizzaTime.stop && orders.isEmpty()) { //Orders - defined in PizzaTime.java queue.
            return;
          }
          takeOrder();
          if (this.order != null) {
            System.out.println("Worker " + this.name + " took order number "
                + this.order.getId());
            PizzaTime.buttonW = false;
            this.busy = true;
            sleep(getEfficiency() * 1000);
            orderReady();
            System.out.println("Order number " + this.order.getId() + " ready!");
            //finishedOrdersQueue.put(order);
            warehouse.placePackage(order);
            free();
          }
        }
        new Thread(this).start();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
