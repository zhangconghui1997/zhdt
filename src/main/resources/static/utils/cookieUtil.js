function setCookie(name, value){
    var exdate = new Date();
    exdate.setTime(exdate.getTime() + 30*60*1000);
    document.cookie = name + "="+ value + ";expires=" + exdate.toGMTString();
}


function getCookie(name){
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
    if(arr != null) return unescape(arr[2]); return '';
}