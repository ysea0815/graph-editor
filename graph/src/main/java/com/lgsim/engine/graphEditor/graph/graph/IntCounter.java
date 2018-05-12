package com.lgsim.engine.graphEditor.graph.graph;

import java.io.Serializable;

// TODO: thread safe?
class IntCounter implements Serializable {

  private int count;


  IntCounter(int count) {
    this.count = count;
  }


  int inc()
  {
    int value;
    if (count != Integer.MAX_VALUE) {
      value = count;
      count += 1;
      return value;
    }
    else {
      throw new RuntimeException("integer counter overflow");
    }
  }


  public int dec()
  {
    int value = count;
    if (count != 0) {
      count -= 1;
    }
    return value;
  }
}
