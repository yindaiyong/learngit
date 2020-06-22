//传入指定日期 1900-01-01 type 1 加一天 -1减一天 （暂不支持多天）
function changeYearMonthDay (date,type){
	var returnDate = new Date(date);
	var fullMonth = [1,3,5,7,8,10,12]; 
	var year = returnDate.getFullYear();
	var month = returnDate.getMonth()+1;
	var day = returnDate.getDate();
	day = day+(type);
	if($.inArray(month,fullMonth) != -1){ //31天的月
		if(day > 31){
			month = month + 1 ;
			day = 1;
			if(month > 12){
				month = 1;
				year = year + 1 ;
			}
		}
		if(day < 0){
			month = month - 1 ;
			day = 31;
			if(month == 0){
				month = 12;
				year = year - 1;
			}
		}
	}else if(month == 2){ //2月
		//闰年 
		if(year % 4  == 0){
			if(day > 29){
				month = month + 1 ;
				day = 1;
				if(month > 12){
					month = 1;
					year = year + 1 ;
				}
			}
			if(day == 0){
				month = month - 1 ;
				day = 31;
				if(month == 0){
					month = 12;
					year = year - 1;
				}
			}
		}else{//平年 
			if(day > 28){
				month = month + 1 ;
				day = 1;
				if(month > 12){
					month = 1;
					year = year + 1 ;
				}
			}
			if(day == 0){
				month = month - 1 ;
				day = 31;
				if(month == 0){
					month = 12;
					year = year - 1;
				}
			}
		}
		
	}else{
		if(day > 30){ //30天的月
			month = month + 1 ;
			day = 1;
			if(month > 12){
				month = 1;
				year = year + 1 ;
			}
		}
		if(day == 0){
			month = month - 1 ;
			day = 30;
			if(month == 0){
				month = 12;
				year = year - 1;
			}
		}
	}
	returnDate = year + '-' + month+'-'+day ;
	return returnDate;
}