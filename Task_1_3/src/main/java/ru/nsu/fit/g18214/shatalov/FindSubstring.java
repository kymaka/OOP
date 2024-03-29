/*
 * This Java source file was generated by the Gradle 'init' task.
 */

package ru.nsu.fit.g18214.shatalov;

import javax.xml.transform.Source;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class FindSubstring {
  /**
   * Opens and reads file character by character and finds all occurrences of given substring.
   * @param substring accepts String
   * @param filename accepts path to file (local or from internet)
   * @return Integer array indexes of all occurrences of given substring
   * @throws IOException if there is no such file
   */
  public Integer[] readAndFind(String substring, String filename) throws IOException {

    BufferedReader br;
    if (filename.contains("http")) {

      br = new BufferedReader(
              new InputStreamReader(
                      new URL(filename).openStream(), "UTF8"
              )
      );
    } else {
      br = new BufferedReader(
              new InputStreamReader(
                      new FileInputStream(filename), "UTF8"
              )
      );
    }
    ArrayList<Integer> index = new ArrayList<>();
    int count = 0;
    int hlp = 0;
    char[] sub = substring.toCharArray();
    while ((hlp = br.read()) != -1) {
      char current = (char) hlp;
      int i = 0;
      br.mark(substring.length());
      while (sub[i] == current) {
        i++;
        if (i < sub.length) {
          hlp = br.read();
          current = (char) hlp;
        } else {
          break;
        }
      }
      br.reset();
      if (i == sub.length) {
        index.add(count);
      }
      count++;
    }
    Integer[] arr = index.toArray(new Integer[index.size()]);
    return arr;
  }
}

