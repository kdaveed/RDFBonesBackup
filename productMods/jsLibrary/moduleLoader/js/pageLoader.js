var pageLoader = {

	init : function() {
		var pageModules = html.getNewDiv()
		$.each(pageElements, function(index, element) {
			switch (element.type) {
			case "table":
					console.log(element.dataKey)
					var table = new TableLoader(element)
					element.table = table
					pageModules.append(table.getContainer())
				break
			case "treeStructure":
				pageModules.append(
					treeLoader.showTree1(element, treeStructure))
				break
			case "treeStructureSingle":
				pageModules.append(
					treeLoader.showTree2(element, treeStructure))
				break
			case "subPage":
				//Normally a page data object were passed but right now the bone editor
				//definition is not constant
				var boneEditor = new BoneEditor(element)
				UIController.modules[element.id] = boneEditor
				pageModules.append(boneEditor.fullScreenContainer)
				//This is just the loading of the frame. It "constructor" shows the sub-
				//page module and fills it with data
				break
			default:
				break
			}
		})
		$("#pageContainer").append(pageModules)
	},

	refreshTables : function(){
		$.each(pageElements, function(index, element) {
			if(element.type == "table"){
				element.table.refresh()
			}
		})
	}
}

$(document).ready(function() {
	pageLoader.init()
	
})
/*
var ajaxCall = function(i){
	return $.ajax({
		url : baseUrl + "skeletalInventoryData",
	    async: false,
		data : {
			dataOperation : "test"
			}
		}).done(function(msg){
			result = $.parseJSON(msg)
			console.log(i)
			if(i == 9){
				console.log("donedonedone")
			}
		})
}
*/