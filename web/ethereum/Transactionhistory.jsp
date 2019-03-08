<%-- 
    Document   : Transactionhistory
    Created on : Nov 27, 2017, 7:47:07 AM
    Author     : panel2
--%>

   
 <ol class="breadcrumb">
        <li class="breadcrumb-item">
    All Transaction Report
        </li>
       
      </ol>
    <div class="card-body">
           <div class="table-responsive">
            <div class="table table-bordered" id="dataTable" width="100%" cellspacing="0">  
                    <table class="table table-bordered nomargin">

                        <thead>
                            <tr>	<th>ID</th>
                                <th>Username </th>
                                <th>Imobicash Sender</th>
                                <th>Imobicash Receiver</th>
                                <th>Imobicash Hash Sender</th>
                                <th>Amount</th>

                            </tr>
                        </thead>

                        <tbody>


                            <tr  ng-repeat="x in myTxt| orderBy:'id'">   
                                <td>{{x.id}} </td>
                                <td> {{x.username}}</td> 
                                <td> {{x.imobicash_sender}}</td>
                                <td> {{x.imobicash_receiver}}</td>
                                <td> {{x.TransactionHash}}</td>
                                <td> {{x.cr}}</td>



                            </tr>
                        </tbody>	</table>     
                </div>
            </div></div>    
