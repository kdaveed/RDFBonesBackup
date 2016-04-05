var BoneEditor = function(data) {

	/*******************************************************************************
	 * Initiate the boneeditor function
	 ******************************************************************************/
	this.data = data
	this.fullScreen = html.getFullScreen(data.id)
	this.container = html.getFullScreenContainer()
	this.close = new Close(this.fullScreen)
	this.backButton = new BackToParent(this, data.parent)
	this.labelEditor = new LiteralEditor(this, "Label", "label", "rdfs:label", true)
	this.descriptionEditor = new LiteralEditor(this, "Description","description","rdfBones:description", false)
	this.imageEditor = new ImageEditor(this, data.images)
	this.subboneEditor = new SubboneEditor(this)
	this.waitingGif = new WaitingGif()
	this.addElements()
	
	this.fullScreen.append(this.container).hide()
}
BoneEditor.prototype.addElements = function(data){
	this.container
	.append(this.close.container)
	.append(this.backButton.container)
	.append(this.labelEditor.container)
	.append(this.descriptionEditor.container)
	.append(this.imageEditor.container)
	.append(this.subboneEditor.container)
	
}
BoneEditor.prototype.show1 = function(data){
	DataController.addBone("addBone")
	this.data = data
	this.fullScreen.show()
	this.backButton.show1(data.parent)
	this.labelEditor.show1(data.label)
	this.descriptionEditor.show1(data.description)
	this.imageEditor.show1(data.images, false)
	this.subboneEditor.show1(data)
}

BoneEditor.prototype.waitForResult = function(){
	this.fullScreen.show()
	this.container.empty()
	this.container.append(this.waitingGif.container)
}
