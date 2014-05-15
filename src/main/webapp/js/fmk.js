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
         * Indique le type de table (detail ou popup)
         */
        tableType: new TableAttrs("type", "popup"),

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
        deleteUri: new TableAttrs("delete-uri", window.location.pathname),

        /**
         * Callback appelé après une modification de ligne
         */
        editCallback: new TableAttrs("edit-cb", null),

        /**
         * Callback appelé après la suppression d'une ligne
         */
        deleteCallback: new TableAttrs("delete-cb", null)
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
     * Ajoute une donnée à l'uri en prennant en compte l'ajout conditionnel du '/' de séparation.
     * @param urlAdd donnée à ajouter à l'uri
     */
    pathResolver: function (urlAdd) {
        var path = window.location.pathname;
        return path.match(/\/$/) ? path + urlAdd : path + "/" + urlAdd;
    },

    displaySuccess: function (result) {
        "use strict";
        var $alert = $('#layout-alert'), $icon = $alert.find('.ico'), $msg = $alert.find('.msg');
        $msg.text(result.message);
        $alert.addClass('alert-success');
        $alert.addClass('in');
    },

    displayError: function (result) {

    },

    /**
     * Sauvegarde la dernière opération effectuée sur la table
     * @param action dernière action
     */
    saveLastAction: function (action) {
        $('#layout-alert').data('lastAction', action);
    },

    revert: function () {
        var action = $('#layout-alert').data('lastAction');
    },

    /**
     * Gère les clics sur les boutons de création d'un nouvel enregistrement et redirige vers la popup ou le détail en
     * fonction du type de table
     * @param $event événement de clic sur le bouton nouveau
     */
    redirectNew: function ($event) {
        var $table = $event.parent('.data-frame').find('table[id^=dt_]'),
            tableType = fmk.getTableAttr($table, fmk.tableAttrs.tableType);
        if (tableType === "detail") {
            window.location.pathname = fmk.pathResolver("new");
        } else {
            //TODO call new popup
        }
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
        var $table = $event.closest('table[id^=dt_]'), dataTable = $table.data('dt');
        if (!$table.length) {
            throw "La table est introuvable. Le nom d'une datatable géré par le framework doit commencer par 'dt_'.";
        }
        $.ajax(fmk.pathResolver(id), {type: 'DELETE'}).done(function (response) {
            if (response.success) {
                fmk.displaySuccess(response);
                if (fmk.getTableAttr($table, fmk.tableAttrs.reload)) {
                    dataTable.ajax.reload();
                } else {
                    dataTable.row($event.closest('tr')).remove().draw();
                }
            } else {
                fmk.displayError(response);
                dataTable.ajax.reload();
            }
        });
    }
};

$(function () {
    "use strict";

    $.fn.extend({

        /**
         * Créé une table gérée par le framework puis une stocke une référence vers la dataTable. Dans le cas, d'une
         * table avec popup gère également la validation de la popup.
         * @param dtOptions paramètres de la table (ref : https://datatables.net/reference/index)
         * @param [$popup] popup à afficher pour les modifications de table
         * @param [validRules] paramètres de validation de la popup (ref : http://jqueryvalidation.org/category/plugin/)
         */
        fmkTable: function (dtOptions, $popup, validRules) {
            var dataTable;
            if (dtOptions.serverSide && !dtOptions.ajax.dataSrc) {
                dtOptions.ajax.dataSrc = 'data';
            }
            dataTable = this.DataTable(dtOptions);
            if ($popup) {
                //TODO later
            } else {
                this.data(fmk.tableAttrs.tableType.field, 'detail');
            }
            this.data('dt', dataTable);
        },

        /**
         * Créé une table gérée par le framework avec une colonne de détail et une colonne de suppression si nécessaire.
         * @param dtOptions paramètres de la table (ref : https://datatables.net/reference/index)
         */
        detailTable: function (dtOptions) {
            var columns = dtOptions.columns, headSize = this.find('thead th').length;
            if (headSize === columns.length + 2) {
                columns.push(fmk.detailCol());
                columns.push(fmk.deleteCol());
            }
            this.fmkTable(dtOptions);
        }
    });
});