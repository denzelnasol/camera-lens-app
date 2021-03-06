package sfu.packages.cmpt276a2;

import java.util.ArrayList;
import java.util.Iterator;
import  java.util.List;

/**
 * Stores collection of lenses and may add or retrieve a lens by index
 */
public class LensManager implements Iterable<Lens>{

    /*
        Singleton Support
     */
    private static LensManager instance;
    private LensManager() {
        // Prevents instantiations
    }
    public static  LensManager getInstance() {
        if (instance == null) {
            instance = new LensManager();
        }

        return instance;
    }

    /*
        Normal Object Code
     */
    public List<Lens> lens = new ArrayList<>();

    public void add(Lens newLens) {
        lens.add(newLens);
    }

    public int size() {
        return lens.size();
    }

    public Lens getLens(int index) {
        return lens.get(index);
    }

    @Override
    public Iterator<Lens> iterator() {
        return lens.iterator();
    }
}
