/**
 * Script para ordenar tablas con jqueryDatatables
 */
  $(function(){
    $("#tabla").dataTable({
    	"sPaginationType":"full_numbers",
    		"oLanguage": {
                "sLengthMenu": "Muestra _MENU_ registros por p�gina",
                "sZeroRecords": "Nada encontrado - Lo siento",
                "sInfo": "Mostrando _START_ de _END_ hasta _TOTAL_ registros",
                "sInfoEmpty": "Mostrado 0 resultados",
                "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
    			"sSearch":"B�squeda",
    			"oPaginate": {
    		        "sFirst": "Primera P�g",
    		        "sPrevious": "P�g Anterior",
    		        "sNext":"P�g Siguiente",
    		        "sLast":"�ltima P�g"
    		       }
    		}
    });
});