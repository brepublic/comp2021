package hk.edu.polyu.comp.comp2021.clevis.controller;

import hk.edu.polyu.comp.comp2021.clevis.model.Shape;

import java.util.List;

/**
 * Listener that receives notifications when the drawable shape set changes.
 */
public interface ClevisListener {
    /**
     * Invoked whenever the controller updates the set of drawable shapes.
     * @param shapesSnapshot immutable snapshot of shapes in Z-order (lower index rendered first)
     */
    void onShapesUpdated(List<Shape> shapesSnapshot);
}

