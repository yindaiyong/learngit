﻿/**
 * 扩展tree，使其可以获取实心节点
 */
$.extend($.fn.tree.methods, {
  getCheckedExt : function(jq) {// 获取checked节点(包括实心)
      var checked = $(jq).tree("getChecked");
      var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
      $.each(checkbox2, function() {
          var node = $.extend({}, $.data(this, "tree-node"), {
              target : this
          });
          checked.push(node);
      });
      return checked;
  },
  getSolidExt : function(jq) {// 获取实心节点
      var checked = [];
      var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
      $.each(checkbox2, function() {
          var node = $.extend({}, $.data(this, "tree-node"), {
              target : this
          });
          checked.push(node);
      });
      return checked;
  }
});


/**
 * 扩展tree，使其支持平滑数据格式
 */
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idField, textField, parentField;
	if (opt.parentField) {
		idField = opt.idField || 'id';
		textField = opt.textField || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idField]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idField] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textField];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textField];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};
