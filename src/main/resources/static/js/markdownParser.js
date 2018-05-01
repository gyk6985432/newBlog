/**
 * Created by gyk on 2018/5/2.
 *
 */

function parser(content) {
    this.update = function () {
        content.innerHTML = markdown.toHTML(content.innerHTML);
    };
    this.update();
}
var get = function (id) {
    return document.getElementById(id);
}
// new parser(get("content"));
