var category = {
		category_add : function () {
			var that = this;
			$.ajax({
				type:"post",
				url: context_path + "/" + authUser + "/admin/category/add",
				data: $("#categoryForm").serialize(),
				success : function (data) {
					if(data=="success")
					{
						that.category_get();
						$("#name").val("");
						$("#description").val("");
					}
				},
				error: function (request, status, error) {
					alert(status);
				}
			});
		},
		category_delete : function (data) {
			var that = this;
			var categoryNo = data;
			$.ajax({
				type:"get",
				url: context_path + "/" + authUser + "/admin/category/delete/"+ categoryNo,
				success : function (response) {
					var result = response.result;
					if(result=="success")
					{
						that.category_get();
						$("#name").val("");
						$("#description").val("");
					}else{
						window.location.href= context_path + "/" + authUser;
					}
				},
				error: function (request, status, error) {
					alert(status);
				}
			});
		},
		category_get : function () {
			var that = this;
			$.ajax({
				type:'GET',
				url : context_path + "/" + authUser + "/admin/category/get",
				dataType: "json",
				success : function (response) {
					console.log(response.result);
					var category = response.data;
					var html = "";
					html += "<table class='admin-cat'>";
					html += "<tr>";
		  			html += "<th>번호</th>"
		  			html += "<th>카테고리명</th>"
		  			html += "<th>포스트 수</th>"
		  			html += "<th>설명</th>"
		  			html += "<th>삭제</th>"
		  			html += "</tr>";
					if(category.length > 0){
						for (var i = 0; i < category.length; i++) {
							html += "<tr>";
							html += "<td>"+ (i+1) +"</td>";
							html += "<td>"+ category[i].name + "</td>";
							html += "<td>"+ category[i].count + "</td>";
							html += "<td>"+ category[i].description + "</td>";
							html += '<td>';
							html += '<form id="deleteForm" name="deleteForm" method="gey">'
							html += '<input type="image" src="/jblog6/assets/images/delete.jpg" onclick="category.category_delete('+category[i].no+')">'
							html += '</td>';
							html += '</form>'
							html += "</tr>";						
						}
					}else{
						html += "<tr>";
						html += "<td colspan='5' style='text-align:center;'>카테고리가 없습니다.</td>";
						html += "</tr>";		
					}
					html += "</table>";
				    $("#categoryList").html(html);
				},
				error: function (request, status, error) {
					console.log("status", status);
				}
			});
		}
		
};