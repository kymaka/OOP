package ru.nsu.fit.g18214.shatalov;

class Elem<O, K> {

  private O element;
  Elem<O, K> nextElem;
  Elem<O, K> preElem;
  K key;

  Elem(K k, O newElem, Elem<O, K> pre) {
    preElem = pre;
    element = newElem;
    key = k;
  }

  void addNextElem(Elem<O, K> next) {
    nextElem = next;
    if (next != null) {
      next.preElem = this;
    }
  }

  O getElem() {
    return element;
  }

  Elem<O, K> getPreElem() {
    return preElem;
  }

  Elem<O, K> getNextElem() {
    return nextElem;
  }
}
