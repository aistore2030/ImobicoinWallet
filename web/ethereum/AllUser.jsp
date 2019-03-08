<%-- 
    Document   : AllUser
    Created on : May 24, 2018, 7:33:49 AM
    Author     : Saksham
--%>

<div class="card mb-3">
   
    <ol class="breadcrumb">
        <li class="breadcrumb-item">
       Manage All {{title}}
        </li>
       
      </ol>
    <%String rolls = String.valueOf(session.getAttribute("roll")).trim();
        //String  username  = String.valueOf(session.getAttribute("username")).trim(); 
        int roll = Integer.parseInt(rolls);%>
    <div class="card-body">
        <div class="table-responsive">
           
                
                    <br>


                    <br>
                                        <!--<div class="alert alert-info">
                        <p>Sort key: {{sortKey}}</p>
                        <p>Reverse: {{reverse}}</p>
                        <p>Search String : {{search}}</p>
                    </div>-->
                
                    <table  class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                            <tr><th ng-click="sort('id')">ID
                                </th>
                               
                    
                                <th ng-click="sort('name')">Name Contact Details
                                </th>
                                <th ng-click="sort('username')">Login
                                </th>
                                <th ng-click="sort('status')">Status And Temp Status
                                </th>
                                <th  >imobicash address
                                </th>
                                <th ng-click="sort('balance')">Balance
                                </th>
                               
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>

                            <tr  ng-repeat="x in myTxt| filter:search:strict | filter:q " >

                                <td>{{x.id}}</td>
                                
                                <td>{{x.name}}<br/>{{x.mobile}}<br/>{{x.email}}</td>
                                <td>{{x.email}}<br/>{{x.password}}</td>

                                <td>{{x.status}}<br />{{x.tempstatus}}</td>
                                <td>{{x.imobicash_address }} </td>
                                <td>{{x.balance}}</td>

                                <td>
                                    <a ng-show="x.status==='UnVerified'" class="btn btn-primary   btn-sm"  ng-click="enableUser(x)">Verify</a><br>
                                    <a ng-show="x.status==='Verified'" class="btn btn-primary  btn-sm"  ng-click="disableUser(x)">UnVerify</a>
                                  
                                </td>
                                    
                            </tr>

                        </tbody>	</table> 
 
                </div> </div></div>