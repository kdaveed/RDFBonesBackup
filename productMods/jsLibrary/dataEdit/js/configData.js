var singleBones = []
var coherentBones = []

var pageElements = [ {
	id : "coherentBones",
	title : "Coherent Bones",
	type : "table",
	dataKey : "coherent",
	dataSet : coherentBones,
	columns : [ {
		columnName : "label",
	}, {
		columnName : "description",
	}, {
		columnName : "images",
		cardinality : true,
		ui : {
			type : "imageList",
			edit : false
		},
		// From the image we know that it will be an
		dataMap : {
			uri : "image",
			src : "src"
		}
	} ]
}, {
	id : "coherentBoneTree",
	title : "Add Coherent Bones",
	type : "treeStructure",
	dataKey : "treeStructure",
}, {
	id : "singleBones",
	title : "Single Bones",
	type : "table",
	dataKey : "single",
	dataSet : singleBones,

	columns : [ {
		columnName : "label",
	}, {
		columnName : "description",
	}, {
		columnName : "images",
		cardinality : true,
		ui : {
			type : "imageList",
			edit : false
		},
		// From the image we know that it will be an
		dataMap : {
			uri : "image",
			src : "src"
		}
	} ]
},{
	id : "singleBoneTree",
	title : "Add Single Bones",
	type : "treeStructureSingle",
	dataKey : "treeStructure",
}, {
	id : "boneEditor",
	type : "subPage",
} ]

console.log(pageElements)

var	treeStructure = [ {
		uri : "http://purl.obolibrary.org/obo/FMA_53672",
		label : "Neurocranium",
		children : [ {
			uri : "http://purl.obolibrary.org/obo/FMA_52735",
			label : "Occipital bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52789",
			label : "Left parietal bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52734",
			label : "Frontal bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52788",
			label : "Right parietal bone"
		}, ]
	}, {
		uri : "http://purl.obolibrary.org/obo/FMA_53673",
		label : "Viscerocranium",
		children : [ {
			uri : "http://purl.obolibrary.org/obo/FMA_52738",
			label : "Right temporal bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53645",
			label : "Right lacrimal bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52893",
			label : "Left zygomatic bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_54737",
			label : "Right inferior nasal concha"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53648",
			label : "Left nasal bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52749",
			label : "Hyoid bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53646",
			label : "Left lacrimal bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_9710",
			label : "Vomer"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52736",
			label : "Sphenoid bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53656",
			label : "Left palatine bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52748",
			label : "Mandible"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53649",
			label : "Right maxilla"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_54738",
			label : "Left inferior nasal concha"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52740",
			label : "Ethmoid"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53647",
			label : "Right nasal bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53650",
			label : "Left maxilla"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_53655",
			label : "Right palatine bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52892",
			label : "Right zygomatic bone"
		}, {
			uri : "http://purl.obolibrary.org/obo/FMA_52739",
			label : "Left temporal bone"
		} ]
	} ]
