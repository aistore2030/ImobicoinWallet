 
    <%@ include file="h.jsp" %>

           
             
 <center id="error_msg" >
     <h2>
            <%
                if (request.getAttribute("msg") != null) {
                    out.print(String.valueOf(request.getAttribute("msg")));
                }
            %>
     </h2>  </center>
          
        
      
    <%@ include file="f.jsp" %>