<%-- 
    Document   : getTransactionHashes
    Created on : Nov 27, 2017, 8:21:58 AM
    Author     : panel2
--%>
<div class="note note-info">
                



    <div class="row">
        
        <div class="col-md-6">
            <div class="panel">
    <div class="panel-body">

<h1>TransactionHashes Report</h1>
 <ul class="nav nav-tabs nav-justified" role="tablist">
                                            <div ng-repeat="x in TransactionHashes">
                                                <li class="list-group-item">{{x.addr}}</li>
                                            </div>
                                        </ul></div></div></div></div>