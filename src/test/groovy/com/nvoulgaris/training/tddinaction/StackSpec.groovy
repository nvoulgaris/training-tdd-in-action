package com.nvoulgaris.training.tddinaction

import spock.lang.Specification

class StackSpec extends Specification {

  Stack stack

  def setup() {
    stack = BoundedStack.make(2)
  }

  def "should be empty when created"() {
    expect:
      stack.isEmpty()
      stack.size() == 0
  }

  def "should increment its size when pushed"() {
    when:
      stack.push(1)
    then:
      stack.size() == 1
  }

  def "should decrement its size when popped"() {
    when:
      stack.push(1)
      stack.pop()
    then:
      stack.size() == 0
  }

  def "should throw an UnderflowException when empty and popped"() {
    when:
      stack.pop()
    then:
      thrown(UnderflowException)
  }

  def "should throw an OverflowException when pushed passed its capacity"() {
    when:
      stack.push(1)
      stack.push(1)
      stack.push(1)
    then:
      thrown(OverflowException)
  }

  def "should throw an IllegalCapacityException when initialized with negative capacity"() {
    when:
      BoundedStack.make(-1)
    then:
      thrown(IllegalCapacityException)
  }

  def "should pop x when x was popped"() {
    when:
      stack.push(1)
    then:
      stack.pop() == 1
  }

  def "should pop y and then x when x and then y were pushed"() {
    when:
      stack.push(1)
      stack.push(2)
    then:
      stack.pop() == 2
      stack.pop() == 1
  }

  def "should throw an UnderflowException when the capacity is zero and it is popped"() {
    given:
      Stack zeroCapacityStack = BoundedStack.make(0)
    when:
      zeroCapacityStack.pop()
    then:
      thrown(UnderflowException)
  }
}
