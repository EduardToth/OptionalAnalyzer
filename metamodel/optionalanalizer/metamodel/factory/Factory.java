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
   public MUncategorizedIsPresentPossibleAntipattern createMUncategorizedIsPresentPossibleAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MUncategorizedIsPresentPossibleAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MUncategorizedIsPresentPossibleAntipattern)instance;
    }
   public MWorkingSet createMWorkingSet(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MWorkingSetImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MWorkingSet)instance;
    }
   public MProject createMProject(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MProjectImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MProject)instance;
    }
   public MAnalysis createMAnalysis(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MAnalysisImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MAnalysis)instance;
    }
   public MRule10sAntipattern createMRule10sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule10sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule10sAntipattern)instance;
    }
   public MCompilationUnit createMCompilationUnit(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MCompilationUnitImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MCompilationUnit)instance;
    }
   public MRule1sAntipattern createMRule1sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule1sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule1sAntipattern)instance;
    }
   public MRule20sAntipattern createMRule20sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule20sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule20sAntipattern)instance;
    }
   public MRule12sAntipattern createMRule12sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule12sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule12sAntipattern)instance;
    }
   public MRule3sAntipattern createMRule3sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule3sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule3sAntipattern)instance;
    }
   public MPackage createMPackage(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MPackageImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MPackage)instance;
    }
   public MRule4sAntipattern createMRule4sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule4sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule4sAntipattern)instance;
    }
   public MRule21sAntipattern createMRule21sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule21sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule21sAntipattern)instance;
    }
   public MRule2sAntipattern createMRule2sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule2sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule2sAntipattern)instance;
    }
   public MRule6sAntipattern createMRule6sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule6sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule6sAntipattern)instance;
    }
   public MRule15sAntipattern createMRule15sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule15sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule15sAntipattern)instance;
    }
   public MRule26sAntipattern createMRule26sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule26sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule26sAntipattern)instance;
    }
   public MRule25sAntipattern createMRule25sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule25sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule25sAntipattern)instance;
    }
   public MRule14sAntipattern createMRule14sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule14sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule14sAntipattern)instance;
    }
   public MRule5sAntipattern createMRule5sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule5sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule5sAntipattern)instance;
    }
   public MRule13sAntipattern createMRule13sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule13sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule13sAntipattern)instance;
    }
   public MRule19sAntipattern createMRule19sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule19sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule19sAntipattern)instance;
    }
   public MRule16sAntipattern createMRule16sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule16sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule16sAntipattern)instance;
    }
   public MRule8sAntipattern createMRule8sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule8sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule8sAntipattern)instance;
    }
   public MRule7sAntipattern createMRule7sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule7sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule7sAntipattern)instance;
    }
   public MRule18sAntipattern createMRule18sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule18sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule18sAntipattern)instance;
    }
   public MRule9sAntipattern createMRule9sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule9sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule9sAntipattern)instance;
    }
   public MRule17sAntipattern createMRule17sAntipattern(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule17sAntipatternImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule17sAntipattern)instance;
    }
}
