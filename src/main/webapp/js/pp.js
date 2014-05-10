var fmk = {

    /**
     * Affiche le contenu d'une colonne de détail avec débranchement vers écran détail
     * @param [width] taille de la colonne (defaut = 100)
     * @returns {{mData: string, sWidth: number, bSearchable: boolean, bSortable: boolean, sClass: string, mRender:
     * Function}}
     */
    detailCol: function (width) {
        "use strict";
        return {
            mData: "id",
            sWidth: width || 70,
            bSearchable: false,
            bSortable: false,
            sClass: "center",
            mRender: function (data) {
                var path = window.location.pathname, detailUrl;
                detailUrl = path.match(/\/$/) ? path + data : path + "/" + data;
                return "<a href='" + detailUrl + "'><i class='fa fa-edit fa-2x'></i></a>";
            }
        };
    }
};