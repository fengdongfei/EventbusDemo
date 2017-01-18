##Eventbus3.0 简单使用,适合初学者使用
![这里写图片描述](https://github.com/fengdongfei/EventbusDemo/blob/master/eventbus.gif)
## 概述
EventBus是一款针对Android优化的发布/订阅事件总线。主要功能是替代Intent,Handler,BroadCast在Fragment，Activity，Service，线程之间传递消息.优点是开销小，代码更优雅。以及将发送者和接收者解耦。
下载EventBus的类库
源码：https://github.com/greenrobot/EventBus

##重点
EventBus还有另外有个不同的函数，他们分别是：

1、onEvent
2、onEventMainThread
3、onEventBackgroundThread
4、onEventAsync

这四种订阅函数都是使用onEvent开头的，它们的功能稍有不同,在介绍不同之前先介绍两个概念：
告知观察者事件发生时通过EventBus.post函数实现，这个过程叫做事件的发布，观察者被告知事件发生叫做事件的接收，是通过下面的订阅函数实现的。

onEvent:如果使用onEvent作为订阅函数，那么该事件在哪个线程发布出来的，onEvent就会在这个线程中运行，也就是说发布事件和接收事件线程在同一个线程。使用这个方法时，在onEvent方法中不能执行耗时操作，如果执行耗时操作容易导致事件分发延迟。
onEventMainThread:如果使用onEventMainThread作为订阅函数，那么不论事件是在哪个线程中发布出来的，onEventMainThread都会在UI线程中执行，接收事件就会在UI线程中运行，这个在Android中是非常有用的，因为在Android中只能在UI线程中跟新UI，所以在onEvnetMainThread方法中是不能执行耗时操作的。
onEventBackground:如果使用onEventBackgrond作为订阅函数，那么如果事件是在UI线程中发布出来的，那么onEventBackground就会在子线程中运行，如果事件本来就是子线程中发布出来的，那么onEventBackground函数直接在该子线程中执行。
onEventAsync：使用这个函数作为订阅函数，那么无论事件在哪个线程发布，都会创建新的子线程在执行onEventAsync.

## demo简介
 本案例发送了二个不同的事件类型,在第一个界面接收,第二个界面发送,看log观察结果

##后续
 本demo是根据不同的事件类在接受的时候做不同的处理,还有一种办法是根据NewsEntity这个bean的某一个字段判断是不同的类型,只需要一个接收器(方法)
 就可以实现..有兴趣的可以更改一下

参考资料 http://blog.csdn.net/harvic880925/article/details/40787203
