package A02_Heap;

import com.sun.jdi.ArrayReference;

import java.util.ArrayList;

public class TaskHeapArrayList {

    /**
     * Internes Task-Array für den Heap
     * Ansonsten keine anderen Variablen verwenden!
     */
    private ArrayList<Task> tasks;

    /**
     * Konstruktor
     */
    public TaskHeapArrayList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Neuen Task in den Heap einfügen
     *
     * @param t Einzufügender Task
     */
    public void insert(Task t) {
        tasks.add(t);
    }

    /**
     * Das oberste Element (mit kleinster Priorität entfernen)
     *
     * @return Task mit kleinster Priorität
     */
    public Task remove() {

        if (tasks.size() <= 0)
            return null;

        int minPrio = 0, prio, indexToRemove = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (i == 0) {
                minPrio = tasks.get(i).getPriority();
                indexToRemove = i;
                continue;
            } else {
                prio = tasks.get(i).getPriority();
                if (prio < minPrio) {
                    minPrio = prio;
                    indexToRemove = i;
                }
            }
        }

        Task taskToRemove = tasks.get(indexToRemove);
        tasks.remove(taskToRemove);
        return taskToRemove;
    }

    private void swim(int pos) {
        // TODO: Your implementation of swim
    }

    private void sink(int pos) {
        // TODO: Your implementation of sink
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int left(int pos) {
        return pos * 2;
    }

    private int right(int pos) {
        return (pos * 2) + 1;
    }

    private boolean exists(int pos) {
        return (pos < tasks.size() && pos > 0);
    }

    private int prio(int pos) {
        return tasks.get(pos).getPriority();
    }

    private void exchange(int pos1, int pos2) {
        Task temp;
        temp = tasks.get(pos1);
        tasks.set(pos1, tasks.get(pos2));
        tasks.set(pos2, temp);
    }

    private boolean hasChildren(int pos) {
        return exists(left(pos));
    }

    private int minChild(int pos) {
        int min, l, r;
        l = left(pos);
        r = right(pos);
        min = l;
        if (exists(r) && prio(r) < prio(l)) {
            min = r;
        }
        return min;
    }

}
