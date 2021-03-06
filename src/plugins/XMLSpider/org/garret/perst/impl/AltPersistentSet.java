package plugins.XMLSpider.org.garret.perst.impl;
import plugins.XMLSpider.org.garret.perst.*;

import  java.util.*;

class AltPersistentSet<T extends IPersistent> extends AltBtree<T> implements IPersistentSet<T> { 
    AltPersistentSet() { 
        type = ClassDescriptor.tpObject;
        unique = true;
    }

    public boolean isEmpty() { 
        return nElems == 0;
    }

    public boolean contains(Object o) {
        if (o instanceof IPersistent) { 
            Key key = new Key((IPersistent)o);
            Iterator i = iterator(key, key, ASCENT_ORDER);
            return i.hasNext();
        }
        return false;
    }
    
    public Object[] toArray() { 
        return toPersistentArray();
    }

    public <E> E[] toArray(E[] arr) { 
        return (E[])super.toArray((T[])arr);
    }

    public boolean add(T obj) { 
        return put(new Key(obj), obj);
    }

    public boolean remove(Object o) { 
        T obj = (T)o;
        return removeIfExists(new BtreeKey(checkKey(new Key(obj)), obj));
    }
    
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Set)) {
            return false;
        }
        Collection c = (Collection) o;
        if (c.size() != size()) {
            return false;
        }
        return containsAll(c);
    }

    public int hashCode() {
        int h = 0;
        Iterator i = iterator();
        while (i.hasNext()) {
            h += ((IPersistent)i.next()).getOid();
        }
        return h;
    }
}
