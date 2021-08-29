package com.nvoulgaris.training.tddinaction;

public class BoundedStack implements Stack {

  private int size = 0;
  private final int capacity;
  private final int[] elements;

  public static Stack make(int capacity) {
    if (capacity < 0) {
      throw new IllegalCapacityException();
    }
    if (capacity == 0) {
      return new ZeroCapacityStack();
    }
    return new BoundedStack(capacity);
  }

  private BoundedStack(int capacity) {
    this.capacity = capacity;
    this.elements = new int[capacity];
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void push(int element) {
    if (this.size == this.capacity) {
      throw new OverflowException();
    }
    this.elements[this.size++] = element;
  }

  @Override
  public int pop() {
    if (this.size == 0) {
      throw new UnderflowException();
    }
    return this.elements[--this.size];
  }

  private static class ZeroCapacityStack implements Stack {

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public int size() {
      return 0;
    }

    @Override
    public void push(int element) {
      throw new OverflowException();
    }

    @Override
    public int pop() {
      throw new UnderflowException();
    }
  }
}
