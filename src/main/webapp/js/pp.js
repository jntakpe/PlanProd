var fmk = {

    /**
     * Affiche le contenu d'une colonne de détail pour débranchement vers écran détail
     * @param [width] taille de la colonne (defaut = 10%)
     * @returns {{data: string, width: (*|number), searchable: boolean, orderable: boolean, className: string, render: render}}
     */
    detailCol: function (width) {
        "use strict";
        return {
            data: "id",
            width: width || 10,
            searchable: false,
            orderable: false,
            className: "center",
            render: function (data) {
                var path = window.location.pathname, detailUrl;
                detailUrl = path.match(/\/$/) ? path + data : path + "/" + data;
                return "<a href='" + detailUrl + "'><i class='fa fa-edit fa-1x5'></i></a>";
            }
        };
    },

    /**
     * Affiche le contenu d'une colonne de modification pour débranchement vers une popup
     * @param [width] taille de la colonne (defaut = 10%)
     * @returns {{data: string, width: (*|number), searchable: boolean, orderable: boolean, className: string, render: render}}
     */
    popupCol: function (width) {
        "use strict";
        return {
            data: "id",
            width: width || 10,
            searchable: false,
            orderable: false,
            className: "center",
            render: function (data) {
                return "<a href='javascript:void(0);' onclick='fmk.displayEditPopup( " + data + ", $(this))'>"
                    + "<i class='fa fa-edit fa-1x5'></i></a>";
            }
        }
    },

    /**
     * Affiche le contenu d'une colonne de suppression
     * @param [width] taille de la colonne (defaut = 10%)
     * @returns {{data: string, width: (*|number), searchable: boolean, orderable: boolean, className: string, render: render}}
     */
    deleteCol: function (width) {
        "use strict";
        return {
            data: "id",
            width: width || 10,
            searchable: false,
            orderable: false,
            className: "center",
            render: function (data) {
                return "<a href='javascript:void(0);' onclick='fmk.removeRow( " + data + ", $(this))'>"
                    + "<i class='fa fa-trash-o fa-1x5'></i></a>";
            }
        }
    }
};

$(function () {
    "use strict";

    var $dt = $('table[id^=dt_]');
    /** Ajout du message de table vide */
    if ($dt.length) {

    }
});