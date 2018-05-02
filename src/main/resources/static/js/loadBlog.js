/**
 * Created by gyk on 2018/5/2.
 */
function load(name) {
    if (name == "null"){
        $(".blog").load("/fragment/default");
    }else {
        $(".blog").load("/fragment/"+name);
    }
}

function showComment() {
    $("#comments").toggle();
}
