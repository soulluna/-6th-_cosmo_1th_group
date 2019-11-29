function schdulWrite(url){
	
	var nextPage = url+"/Main/SchedulFormWrite.do";
	window.open(nextPage, "_blank_1","toolbar=no, scrollbars=yes, menubar=no, resizeable=no, width=500, height=800");
}

function schdulDetails(url, schnum){
	
	var nextPage = url+"/Main/schdulDetail.do?schnum="+schnum;
	window.open(nextPage, "_blank_1","toolbar=no, scrollbars=yes, menubar=no, resizeable=no, width=500, height=800");
}