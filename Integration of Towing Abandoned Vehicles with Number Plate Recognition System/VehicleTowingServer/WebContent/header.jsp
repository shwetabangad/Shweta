<script type="text/javascript">
	// toggle dynamic divs
    function toggle(div) {
    	var elem = document.getElementById(div);
    	if (elem.style.display=='') {elem.style.display='none'; return;}
    	elem.style.display='';
    }
</script>

<div id="header">
    <div id="logo">
      <h1>Vehicle Towing<sup>server</sup></h1>
    </div>
    <div id="search"> <a onclick="toggle('searchform');">+ SEARCH</a>
      <div id="searchform" style="display: none;">
        <form method="post" action="https://www.google.co.in/">
          <p>
            <input class="searchfield" name="search_query" id="keywords" value="Search Keywords" type="text" />
            <input class="searchbutton" name="submit" value="Search" type="submit" />
          </p>
        </form>
      </div>
    </div>
  </div>