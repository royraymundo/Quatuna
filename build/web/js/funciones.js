$(document).ready(function(){
    $("tr #btnDelete").click(function(){
        var idp = $(this).parent().find("#idp").val();
        swal({
            title: "Esta seguro de eliminar?",
            text: "Once deleted, you will not be able to recover this imaginary file!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }) .then((willDelete) => {
                    if (willDelete) {
                        eliminar(idp);
                        swal("Poof! Your imaginary file has been deleted!", {
                            icon: "success",
                        }).then((willDelete)=> {
                            if(willDelete){
                                parent.location.href="Control?accion=Carrito";
                            }
                        });
                    } else {
                        swal("Registro no Eliminado");
                    }
                });
        
     });
    function eliminar(idp) {
        var url = "Control?accion=Delete";
        $.ajax({
            type: 'POST',
            url: url,
            data: "idp="+idp,
            success: function (data, textStatus, jqXHR) {
                alert("Registro Eliminado"+idp);
            }
        });
    }
});