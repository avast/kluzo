package com.avast.kluzo

import java.util.concurrent.{Callable, Executor, ExecutorService, Future, TimeUnit}

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, ExecutionContextExecutorService}

object Kluzo {

  def wrapExecutionContext(ec: ExecutionContext): ExecutionContext = new ExecutionContext {
    override def execute(runnable: Runnable) = ec.execute(new KluzoRunnable(runnable))

    override def reportFailure(cause: Throwable) = ec.reportFailure(cause)
  }

  def wrapExecutionContextExecutor(ec: ExecutionContextExecutor): ExecutionContextExecutor = new ExecutionContextExecutor {
    override def execute(runnable: Runnable) = ec.execute(new KluzoRunnable(runnable))

    override def reportFailure(cause: Throwable) = ec.reportFailure(cause)
  }

  def wrapExecutionContextExecutorService(ec: ExecutionContextExecutorService): ExecutionContextExecutorService = new ExecutionContextExecutorService {
    override def execute(runnable: Runnable) = ec.execute(new KluzoRunnable(runnable))

    override def reportFailure(cause: Throwable) = ec.reportFailure(cause)

    override def submit(task: Runnable): Future[_] = ec.submit(new KluzoRunnable(task))

    override def submit[T](task: Runnable, result: T): Future[T] = ec.submit(new KluzoRunnable(task), result)

    override def submit[T](task: Callable[T]): Future[T] = ec.submit(new KluzoCallable(task))

    override def invokeAny[T](tasks: java.util.Collection[_ <: Callable[T]]): T = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      ec.invokeAny(mappedTasks)
    }

    override def invokeAll[T](tasks: java.util.Collection[_ <: Callable[T]]): java.util.List[Future[T]] = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      ec.invokeAll(mappedTasks)
    }

    override def invokeAny[T](tasks: java.util.Collection[_ <: Callable[T]], timeout: Long, unit: TimeUnit): T = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      ec.invokeAny(mappedTasks, timeout, unit)
    }

    override def invokeAll[T](tasks: java.util.Collection[_ <: Callable[T]], timeout: Long, unit: TimeUnit): java.util.List[Future[T]] = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      ec.invokeAll(mappedTasks, timeout, unit)
    }

    override def shutdown() = ec.shutdown()

    override def shutdownNow() = ec.shutdownNow()

    override def isShutdown = ec.isShutdown

    override def awaitTermination(timeout: Long, unit: TimeUnit) = ec.awaitTermination(timeout, unit)

    override def isTerminated = ec.isTerminated
  }

  def wrapExecutor(executor: Executor): Executor = new Executor {
    override def execute(runnable: Runnable) = executor.execute(new KluzoRunnable(runnable))
  }

  def wrapExecutorService(executor: ExecutorService): Executor = new ExecutorService {
    override def execute(runnable: Runnable) = executor.execute(new KluzoRunnable(runnable))

    override def submit(task: Runnable): Future[_] = executor.submit(new KluzoRunnable(task))

    override def submit[T](task: Runnable, result: T): Future[T] = executor.submit(new KluzoRunnable(task), result)

    override def submit[T](task: Callable[T]): Future[T] = executor.submit(new KluzoCallable(task))

    override def invokeAny[T](tasks: java.util.Collection[_ <: Callable[T]]): T = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      executor.invokeAny(mappedTasks)
    }

    override def invokeAll[T](tasks: java.util.Collection[_ <: Callable[T]]): java.util.List[Future[T]] = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      executor.invokeAll(mappedTasks)
    }

    override def invokeAny[T](tasks: java.util.Collection[_ <: Callable[T]], timeout: Long, unit: TimeUnit): T = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      executor.invokeAny(mappedTasks, timeout, unit)
    }

    override def invokeAll[T](tasks: java.util.Collection[_ <: Callable[T]], timeout: Long, unit: TimeUnit): java.util.List[Future[T]] = {
      val mappedTasks = tasks.asScala.map(new KluzoCallable(_)).toList.asJava
      executor.invokeAll(mappedTasks, timeout, unit)
    }

    override def shutdown() = executor.shutdown()

    override def shutdownNow() = executor.shutdownNow()

    override def isShutdown = executor.isShutdown

    override def awaitTermination(timeout: Long, unit: TimeUnit) = executor.awaitTermination(timeout, unit)

    override def isTerminated = executor.isTerminated
  }

}
