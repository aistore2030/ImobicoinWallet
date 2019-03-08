<%-- 
    Document   : profile
    Created on : Nov 15, 2017, 3:34:00 PM
    Author     : mala singh
--%>
<div class="note note-info">



  <div class="card " style="width:1085px; height:90px; margin-top:-16px;">
        <div  class=" row ">
    <div class="col-md-9">
      <h3> Be your own bank.</h3>
    </div>
        <div class="col-md-3" >
            <h3 >Total Balance</h3><br>
            {{x.balance}}
        </div>
        </div>
      </div>
    <div class="row">

        <div class="col-md-6">
            <div class="panel">
                <div class="panel-body">
                    <form> <h3> All Details </h3><b>Balance:</b>{{x.balance}}
                        <div class="form-group">
                            <b>Imobicash :</b>{{x.imobicash_address}}
                            <label for="name">Name </label>
                            <input type="text" class="form-control" id="call_back_url" ng-model="x.name" value= "{{x.name}}">
                        </div>

                        <div class="form-group">
                            <label for="email">Email </label>
                            <input type="text" class="form-control" id="call_back_url" ng-model="x.email"  value= "{{x.email}}" >
                        </div>


                        <div class="form-group">
                            <label for="mobile">Mobile </label>
                            <input type="text" class="form-control" id="call_back_url"  ng-model="x.phone"  value= "{{x.phone}}" >
                        </div>
                        <button type="submit" ng-click="UpdateDetails(x)" class="btn btn-success">Submit</button>

                    </form>


                </div>
            </div> </div><div class="col-md-6">
                
            </div>
                
    </div>
                
</div> 