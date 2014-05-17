/* To keep misc functions
 * 
 */

function validateSearchForm(sForm){
	if(sForm["q"].value == ""){
		alert("Please enter search keyword");
	}else{
		setXMLHttpRequest(sForm.action+'?q='+encodeURIComponent(sForm["q"].value),'container');
	}
}