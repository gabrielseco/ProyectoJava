/**
 * Script para ordenar tablas con jqueryDatatables
 */
  $(function(){
    $("#tabla").dataTable({
    	"sPaginationType":"full_numbers",
    		"oLanguage": {
                "sLengthMenu": "Muestra _MENU_ registros por página",
                "sZeroRecords": "Nada encontrado - Lo siento",
                "sInfo": "Mostrando _START_ de _END_ hasta _TOTAL_ registros",
                "sInfoEmpty": "Mostrado 0 resultados",
                "sInfoFiltered": "(filtered from _MAX_ total records)",
    			"sSearch":"Búsqueda",
    			"oPaginate": {
    		        "sFirst": "Primera Pág",
    		        "sPrevious": "Pág Anterior",
    		        "sNext":"Pág Siguiente",
    		        "sLast":"Última Pág"
    		       }
    		}
    });
});