package com.github.jntakpe.fmk.web;

import com.github.jntakpe.fmk.domain.GenericDomain;

/**
 * Contrôlleur générique contenant les méthodes communes aux écrans de type liste + popup.
 *
 * @author jntakpe
 */
public abstract class PopupController<T extends GenericDomain<S>, S extends Number> extends ListController<T, S> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ListType getListType() {
        return ListType.POPUP;
    }


}
