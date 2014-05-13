/**
 * Construit un objet définissant un attribut d'une DataTable
 * @param field nom de l'attribut html5 (devra être précédé de data-)
 * @param defaultVal valeur par défaut de l'attribut
 * @constructor
 */
function TableAttrs(field, defaultVal) {
    "use strict";
    this.field = field;
    this.defaultVal = defaultVal;
}

var fmk = {

    /**
     * Attributs de DataTables gérés par le framework
     */
    tableAttrs: {
        /**
         * Indique si les données doivent être rechargées après chaque opération sur la table
         */
        reload: new TableAttrs("reload", false),

        /**
         * Indique l'id de la popup permettant d'éditer la table (utile en cas de plusieurs popup)
         */
        editPopupId: new TableAttrs("edit-popup", "dt-popup"),

        /**
         * Base de l'uri afin de pouvoir appeler un contrôleur différent pour l'édition. Par exemple, si notre url courante est
         * '/fmk/param' et que nous souhaitons appeler l'url '/fmk/toto', la valeur doit être '/fmk/toto'
         */
        editUri: new TableAttrs("edit-uri", window.location.pathname),

        /**
         * Base de l'uri afin de pouvoir appeler un contrôleur différent pour la suppression. Par exemple, si notre url courante est
         * '/fmk/param' et que nous souhaitons appeler l'url '/fmk/toto', la valeur doit être '/fmk/toto'.
         */
        deleteUri: new TableAttrs("delete-uri", window.location.pathname)
    },

    /**
     * Renvoi la valeur d'un attribut de la table et s'il existe sinon sa valeur par défaut
     * @param $table objet jQuery représentant la table
     * @param tableAttr TableAttrs contenant le champ à rechercher et sa valeur par défaut
     * @returns {*} valeur de l'attribut
     */
    getTableAttr: function ($table, tableAttr) {
        "use strict";
        if (!tableAttr) {
            throw "Attribut non géré par le framework";
        }
        if (!tableAttr instanceof TableAttrs) {
            throw "Le paramètre 'tableAttr' de la méthode doit être une instance de 'TableAttrs'";
        }
        var res = $table.data(tableAttr.field);
        return res === undefined ? tableAttr.defaultVal : res;
    },

    /**
     * Ajoute l'id de l'enregistrement courant à uri en prennant en compte l'ajout conditionnel du '/' de séparation.
     * @param id identifiant de l'enregistrement courant
     */
    pathResolver: function (id) {
        var path = window.location.pathname;
        return path.match(/\/$/) ? path + id : path + "/" + id;
    },

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
                return "<a href='" + fmk.pathResolver(data) + "'><i class='fa fa-edit fa-1x5'></i></a>";
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
                return "<a href='javascript:void(0);' onclick='fmk.displayEditPopup(" + data + ", $(this))'>"
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
                return "<a href='javascript:void(0);' onclick='fmk.removeRow(" + data + ", $(this))'>"
                    + "<i class='fa fa-trash-o fa-1x5'></i></a>";
            }
        }
    },

    /**
     * Supprime une ligne de la table
     * @param id identifiant de la ligne
     * @param $event événnement ayant déclenché l'appel de la méthode
     */
    removeRow: function (id, $event) {
        "use strict";
        var $table = $event.closest("table[id^=dt_]");
        if (!$table.length) {
            throw "La table est introuvable. Le nom d'une datatable géré par le framework doit commencer par 'dt_'.";
        }
        $.ajax(fmk.pathResolver(id), {type: 'DELETE'}).done(alert('YEAH BABY'));
    }
};

$(function () {
    "use strict";

});