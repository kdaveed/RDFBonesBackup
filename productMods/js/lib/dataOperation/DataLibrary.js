

var DataLib = {
		
	isArray : function(someVar){
		if( Object.prototype.toString.call( someVar ) === '[object Array]' ) {
		    return true
		}
		return false
	},	
		
	getType : function(someVar){
		if ((typeof someVar) == "object") {
			if(this.isArray(someVar)){
				return "array"
			} else {
				return "object"
			}
		} else {
			return "data" 
		}
	},
	
	or : function(variable, values){
		var ret = false
		$.each(values, function(index, value){
			if(variable === value){
				ret = true
				return false
			}
		})
		return ret
	},
	
	typeCheck : function(variable, values){
		return this.or(this.getType(variable), values)
	},
	
	removeObjectFromArrayByKey : function(array, key, value){

		$.each(array, function(index, object){
			if(object[key] == value){
				array.splice(index, 1)
				return false
			}
		})
	},
	
	debugObject : function(object){
		
		var str = ""
		switch(DataLib.getType(object)){
			
		case "object" :
			str += "{ \n "
			$.each(object, function(key, value){
				str += "\n\t" + key + " : " + DataLib.debugObject(value) + " , "
			})
			str += " \n }"
			break
		case "data" : 
			str += "'" + object + "'"
			break
		case "array" :
			str = "[ \n "
			$.each(object, function(key, value){
				str += DataLib.debugObject(value) + " ,  \n"
			})
			str += "\n ]"
			break
		}
		
		return str 
	}
	
}