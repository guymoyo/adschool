
		$(document).ready(function(){
		   $("#zone-bar li a").click(function() {
		   		var hidden = $(this).parents("li").children("ul").is(":hidden");
				$("#zone-bar>ul>li>ul").hide();       
			   	$("#zone-bar>ul>li>a").removeClass();
			   		
			   	if (hidden) {
			   		$(this)
				   		.parents("li").children("ul").toggle()
				   		.parents("li").children("a").addClass("zoneCur");
				   	} 
			   });
		   
		 /*  function cacheAll(){
			   
			   dojo.query(".module").forEach(function(node, index, arr){
				   dojo.query("#"+node.id).style("display", "none");
			   });
		   }
		   
		   dojo.query(".mod").forEach(function(node, index, arr){

				dojo.connect(node, 'onclick', function(evt){
					console.log(node);
					cacheAll();
					dojo.query("#"+node.id+"Mod").style("display", "block");
					console.log(dojo.query("#"+node.id+"Mod"));
					
				});
		   });*/
		   
		});