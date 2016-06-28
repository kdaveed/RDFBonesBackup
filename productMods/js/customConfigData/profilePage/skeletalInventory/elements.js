coherentData = {

	type : sw.dataField,
	textValue : {
		type : sw.local,
		key : "boneLabel"
	},
	linkDataInputs : [ {
		type : sw.local,
		key : "boneUri",
		varName : "classUri",
	}, {
		type : sw.global,
		key : "individual",
	}, {
		type : sw.constant,
		key : "pageUri",
		value : "coherentBones",
	} ],
	mapping : "pageLoader",
	dataToDisplay : {
		type : sw.selectOperationResult,
		dataToSelect : {
			type : sw.global,
			key : "coherentBones",
		},
		selectField : "type",
		selectCriteria : {
			type : sw.local,
			key : "classSelection",
		}
	},
	dataFields : [ {
		type : sw.literalField,
		title : "Label",
		value : {
			type : sw.local,
			key : "label",
		}
	}, {
		type : sw.literalFieldMiddle,
		title : "Number of Bone Organs",
		value : {
			type : sw.local,
			key : "boneOrganCount",
		}
	}, {
		type : sw.editButton,
		linkDataInputs : [ {
			type : sw.global,
			key : "individual",
			varName : "skeletalInventory",
		}, {
			type : sw.constant,
			key : "pageUri",
			value : "boneDivision",
		}, {
			type : sw.local,
			key : "boneDivision",
			varName : "individual",
		}, {
			type : sw.local,
			key : "type",
			varName : "existingBoneDivisionType",
		}, {
			type : sw.local,
			key : "boneUri",
			varName : "classUri",
		} ],
		mapping : "pageLoader",
	} ]
}