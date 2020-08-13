package optionalanalizer.metamodel.factory;

import ro.lrg.xcore.metametamodel.XEntity;
import optionalanalizer.metamodel.entity.*;
import optionalanalizer.metamodel.impl.*;

public class Factory {
   protected static Factory singleInstance = new Factory();
   public static Factory getInstance() { return singleInstance;}
   protected Factory() {}
   private LRUCache<Object, XEntity> lruCache_ = new LRUCache<>(1000);
   public void setCacheCapacity(int capacity) {
       lruCache_.setCapacity(capacity);
   }
   public void clearCache() {lruCache_.clear();}
   public MInvocation createMInvocation(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MInvocationImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MInvocation)instance;
    }
   public MIfStatement createMIfStatement(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MIfStatementImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MIfStatement)instance;
    }
   public MAssignment createMAssignment(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MAssignmentImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MAssignment)instance;
    }
   public MCompilationUnit createMCompilationUnit(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MCompilationUnitImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MCompilationUnit)instance;
    }
   public MProject createMProject(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MProjectImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MProject)instance;
    }
}
