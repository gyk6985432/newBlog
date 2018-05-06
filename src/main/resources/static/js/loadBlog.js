/**
 * Created by gyk on 2018/5/2.
 */
function load(name) {
    if (name == "null"){
        $(".blog").load("/1/fragment/1");
    }else {
        $(".blog").load("/1/fragment/"+name);
    }
}

function showComment() {
    $("#comments").toggle();
}
