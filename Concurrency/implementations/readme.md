## important concepts :
- spurious-wakeup, monitor-lock
- different ways to implement thread
- stop, resume, suspend is deprecated ==> why ????
- join(), Thread Priority
- reentrantLock, readWriteLock
- pessimistic lock vs optimistic lock
- semaphores, stamped lock, condition
- Condition [ await, signal ] , wait, notify
- ThreadPoolExecutor
- 



--------------------------------------------------------------
| Lock Based Mechanism | Lock Free Mechanism                |
|----------------------|------------------------------------|
| Synchronized         | CAS Operation ( Compare and swap ) |
| Reentrant            |                                    |
| Stamped              | Atomic Integer                     |
| ReadWrite            | Atomic Boolean                     |
| Semaphores           | Atomic Long                        |
| -                    | Atomic Reference                   |
---------------------------------------------------------------