 
             
    <%@ include file="h.jsp" %>
            <form class="login-form" action="LoginServlet" method="post">
                <h3 class="form-title font-green">Sign In</h3>
                
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">E mail</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="E mail" name="email" /> </div>
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">Password</label>
                    <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password" /> </div>
                <div class="form-actions">
                    <button type="submit" class="btn green uppercase">Login</button>
                   
                    
                </div>

                    <div class="create-account">
                    <p>
                        <a href="register.jsp" id="register-btn" class="uppercase">Create an account</a>
                    </p>
                </div>
            </form>
           
             
    <%@ include file="f.jsp" %>