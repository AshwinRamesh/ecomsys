/* To keep misc functions
 * 
 */

function submitSearchForm(sForm){
	if(validateSearchForm(sForm)){
		setXMLHttpRequest(sForm.action+'?q='+encodeURIComponent(sForm["q"].value),'container');
	}
}

function validateSearchForm(sForm){
	if(sForm["q"].value == ""){
		alert("Please enter search keyword");
		return false;
	}else{
		return true;
	}
}