<%-- 
    Document   : SendTransaction
    Created on : Nov 23, 2017, 1:47:06 PM
    Author     : mala singh
--%>

<div class="card-body">
<form>
    <div class="form-group"> <label  > Transaction from : </label>
        <input class="form-control"  value="{{x.username}}" type="text" readonly ng-model="x.username" /></div>
    <div class="form-group"> <label  > Sender Imobicash Address : </label>
        <input class="form-control"  value="{{x.imobicash_address}}" type="text" readonly ng-model="x.imobicash_address" /></div>
    <!--<div class="form-group"><label  > Transaction to : </label>
        <input class="form-control" type="text"  name="username" /></div>-->
    <div class="form-group"> <label  > Receiver   Imobicash Address : </label>
        <input class="form-control"  type="text"  ng-model="x.imobicash_receiver" required/></div>
    <div class="form-group"> <label  > Transaction Price : </label>
        <input class="form-control"  type="number"  ng-model="x.gas_price" required/></div>
    <div class="form-group">	 <label  >Amount </label>
        <input class="form-control" type="number" ng-model="x.cr" required/></div>  

    <div class="form-group">
        <input type="button" ng-click="Submit(x)" class="btn btn-success" value="submit" /></div>

</form>
    </div>