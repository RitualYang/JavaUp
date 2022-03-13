package com.base.threadPool;

import java.util.concurrent.*;

/**
 * @author wty
 * @create 2020-01-06 18:19
 */
public class ThreadPool {

    public static void main(String[] args) {

        /**
         * 	corePoolSize：线程池中核心线程数的最大值
         * 	maximumPoolSize：线程池中能拥有最多线程数
         * 	workQueue：用于缓存任务的阻塞队列,对于不同的应用场景我们可能会采取不同的排队策略,
         * 	    这就需要不同类型的阻塞队列,在线程池中常用的阻塞队列有以下2种：
         * 	    SynchronousQueue<Runnable>：此队列中不缓存任何一个任务。
         * 	        向线程池提交任务时,如果没有空闲线程来运行任务,则入列操作会阻塞。当有线程来获取任务时,出列操作会
         * 	        唤醒执行入列操作的线程。从这个特性来看,SynchronousQueue是一个无界队列,因此当使用SynchronousQueue
         * 	        作为线程池的阻塞队列时,参数maximumPoolSizes没有任何作用。
         * 	    LinkedBlockingQueue<Runnable>：顾名思义是用链表实现的队列,可以是有界的,也可以是无界的,但在Executors
         * 	        中默认使用无界的,最大长度为Integer.MAX_VALUE,由于长度过长可能导致内存溢出(OOM异常)。
         * 	    ArrayBlockingQueue<Runnable>:  有界队列，基于数组实现。在线程池初始化时，指定队列的容量，后续无法再调整。
         * 	        这种有界队列有利于防止资源耗尽，但可能更难调整和控制。
         * 	  其他四种队列:
         * 	    PriorityBlockingQueue： 支持优先级排序的无界阻塞队列。存放在PriorityBlockingQueue中的元素必须实现Comparable接口，这样才能通过实现compareTo()方法进行排序。优先级最高的元素将始终排在队列的头部；PriorityBlockingQueue不会保证优先级一样的元素的排序，也不保证当前队列中除了优先级最高的元素以外的元素，随时处于正确排序的位置。
         *      DelayQueue： 延迟队列。基于二叉堆实现，同时具备：无界队列、阻塞队列、优先队列的特征。DelayQueue延迟队列中存放的对象，必须是实现Delayed接口的类对象。通过执行时延从队列中提取任务，时间没到任务取不出来。更多内容请见DelayQueue。
         *      LinkedBlockingDeque： 双端队列。基于链表实现，既可以从尾部插入/取出元素，还可以从头部插入元素/取出元素。
         *      LinkedTransferQueue： 由链表结构组成的无界阻塞队列。这个队列比较特别的时，采用一种预占模式，意思就是消费者线程取元素时，如果队列不为空，则直接取走数据，若队列为空，那就生成一个节点（节点元素为null）入队，然后消费者线程被等待在这个节点上，后面生产者线程入队时发现有一个元素为null的节点，生产者线程就不入队了，直接就将元素填充到该节点，并唤醒该节点等待的线程，被唤醒的消费者线程取走元素。
         * 	keepAliveTime：表示空闲线程的存活时间。
         * 	unit：表示keepAliveTime的单位。
         * 	handler：表示当workQueue已满,且池中的线程数达到maximumPoolSize时,线程池拒绝添加新任务时采取的策略。一般可以采取以下四种取值。
         * 	    ThreadPoolExecutor.AbortPolicy()	抛出RejectedExecutionException异常
         *      ThreadPoolExecutor.CallerRunsPolicy()	由向线程池提交任务的线程来执行该任务
         *      ThreadPoolExecutor.DiscardOldestPolicy()	抛弃最旧的任务（最先提交而没有得到执行的任务）
         *      ThreadPoolExecutor.DiscardPolicy()	抛弃当前的任务
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                20,
                30L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.AbortPolicy());
        //threadPoolExecutor.execute();
        /**
         * 创建一个可缓存线程池,如果线程池长度超过处理需要,可灵活回收空闲线程,若无可回收,则新建线程。
         * 1.创建数量几乎没有限制(最大数目为Interger.MAX_VALUE)
         * 2.空闲的工作线程会自动销毁,有新任务会重新创建
         * 3.在使用CachedThreadPool时,一定要注意控制任务的数量,
         *      否则,由于大量线程同时运行,很有会造成系统瘫痪。
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        /**
         * 创建一个指定工作线程数量的线程池。每当提交一个任务就创建一个工作线程,如果工作线程数量达到线程
         *      池初始的最大数,则将提交的任务存入到池队列中。
         * 优点：具有线程池提高程序效率和节省创建线程时所耗的开销。
         * 缺点：在线程池空闲时,即线程池中没有可运行任务时,它不会释放工作线程,还会占用一定的系统资源。
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        /**
         * 创建一个单线程化的线程池,只创建唯一的工作者线程来执行任务,它只会用唯一的工作线程来执行任务
         *      ,保证所有任务按照指定顺序(FIFO, LIFO,优先级)执行。如果这个线程异常结束,会有另一个
         *      取代它,保证顺序执行。
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        /**
         * 创建一个定长的线程池,而且支持定时的以及周期性的任务执行,支持定时及周期性任务执行。
         */
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);

    }
}
