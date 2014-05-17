function setXMLHttpRequest(url,resultContent)
{
	alert(url);
  try
  {
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	  
	xmlhttp = new XMLHttpRequest();
    // create request object
    // register event handler
	xmlhttp.onreadystatechange = function stateChange()
    {
      if ( xmlhttp.readyState == 4 && xmlhttp.status == 200 )
      {
        document.getElementById( resultContent ).innerHTML = xmlhttp.responseText;
        // places text in contentArea
      }
      // end if
    }
    // end function stateChange;
	xmlhttp.open( 'GET', url, true );
    // prepare the request
	xmlhttp.send( null );
    // send the request
  }
  catch ( exception )
  {
    alert( 'Request failed.' );
  }
  // end catch
}