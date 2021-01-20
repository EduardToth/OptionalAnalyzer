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
   public MRule9Atom createMRule9Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule9AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule9Atom)instance;
    }
   public MWorkingSet createMWorkingSet(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MWorkingSetImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MWorkingSet)instance;
    }
   public MRule19Atom createMRule19Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule19AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule19Atom)instance;
    }
   public MRule13Atom createMRule13Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule13AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule13Atom)instance;
    }
   public MRule26Atom createMRule26Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule26AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule26Atom)instance;
    }
   public MProject createMProject(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MProjectImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MProject)instance;
    }
   public MRule3Atom createMRule3Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule3AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule3Atom)instance;
    }
   public MRule7Atom createMRule7Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule7AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule7Atom)instance;
    }
   public MRule12Atom createMRule12Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule12AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule12Atom)instance;
    }
   public MAnalysis createMAnalysis(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MAnalysisImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MAnalysis)instance;
    }
   public MRule21Atom createMRule21Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule21AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule21Atom)instance;
    }
   public MRule20Atom createMRule20Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule20AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule20Atom)instance;
    }
   public MRule25Atom createMRule25Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule25AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule25Atom)instance;
    }
   public MRule2Atom createMRule2Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule2AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule2Atom)instance;
    }
   public MCompilationUnit createMCompilationUnit(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MCompilationUnitImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MCompilationUnit)instance;
    }
   public MRule16Atom createMRule16Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule16AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule16Atom)instance;
    }
   public MRule6Atom createMRule6Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule6AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule6Atom)instance;
    }
   public MRule1Atom createMRule1Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule1AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule1Atom)instance;
    }
   public MRule15Atom createMRule15Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule15AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule15Atom)instance;
    }
   public MRule5Atom createMRule5Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule5AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule5Atom)instance;
    }
   public MRule17Atom createMRule17Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule17AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule17Atom)instance;
    }
   public MRule10Atom createMRule10Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule10AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule10Atom)instance;
    }
   public MUncategorizedIsPresentAtom createMUncategorizedIsPresentAtom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MUncategorizedIsPresentAtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MUncategorizedIsPresentAtom)instance;
    }
   public MRule14Atom createMRule14Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule14AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule14Atom)instance;
    }
   public MRule8Atom createMRule8Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule8AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule8Atom)instance;
    }
   public MRule18Atom createMRule18Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule18AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule18Atom)instance;
    }
   public MRule4Atom createMRule4Atom(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new MRule4AtomImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (MRule4Atom)instance;
    }
}
