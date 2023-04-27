<template>
  <div>
    <div class="header">
      <h3 style="text-align: left">&nbsp;</h3>
      <h3 style="text-align: left">Edit Owner></h3>
    </div>
    <div class="container2">
      <div class="row3">
        <div class="col-sm-4">
          <Button
            class="btn1"
            text="Select Owner"
            color="lightblue"
            disabled
            role="link"
          ></Button>
        </div>
        <div class="col-sm-6">
          <select v-model="selected_owner_ssn" v-on:change="selectedOwner()">
            <option value="">Select an Owner</option>
            <option v-for="owner in owners" :key="owner.ssn" :value="owner.ssn">
              {{ owner.firstName }} {{ owner.lastName }}
            </option>
          </select>
          <div class="row3"></div>
        </div>
      </div>
    </div>
    <div class="container2">
      <div class="row3">
        <div class="col1">
          <Button class="btn1" text="SSN" color="lightblue" disabled></Button>
        </div>
        <div class="col2">
          <input v-model="updateOwner.ssn" placeholder="SSN" disabled />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="FirstName"
            color="lightblue"
            disabled
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateOwner.firstName" placeholder="FirstName" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="LastName"
            color="lightblue"
            disabled
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateOwner.lastName" placeholder="LastName" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Number of Dependents"
            color="lightblue"
            disabled
          ></Button>
        </div>
        <div class="col2">
          <input
            v-model="updateOwner.numDependents"
            placeholder="Number of Dependents"
          />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Annual Income"
            color="lightblue"
            disabled
          ></Button>
        </div>
        <div class="col2">
          <input
            v-model="updateOwner.annualIncome"
            placeholder="Annual Income"
          />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Date of Birth"
            color="lightblue"
            disabled
          ></Button>
        </div>
        <div class="col2">
          <input
            v-model="updateOwner.dateOfBirth"
            placeholder="Date of Birth"
          />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button
            class="btn1"
            text="Profession"
            color="lightblue"
            disabled
          ></Button>
        </div>
        <div class="col2">
          <input v-model="updateOwner.profession" placeholder="Profession" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button class="btn1" text="Phone" color="lightblue" disabled></Button>
        </div>
        <div class="col2">
          <input v-model="updateOwner.phone" placeholder="Phone number" />
        </div>
      </div>
      <div class="row3">
        <div class="col1">
          <Button class="btn1" text="Email" color="lightblue" disabled></Button>
        </div>
        <div class="col2">
          <input v-model="updateOwner.email" placeholder="OwnerEmail" />
        </div>
      </div>
      <div class="row3">
        <div class="col1"></div>
        <div class="col2">
          <Button
            class="btn1"
            text="Update Owner"
            color="lightgreen"
            v-on:click="this.UpdateOwner()"
          ></Button>
        </div>
      </div>
      <div class="row3">
        <div class="col1"></div>
        <div class="col2">
          {{ success_message }}
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Button from "../components/Button";
import OwnerService from "@/services/OwnerService";
export default {
  name: "EditOwner",
  components: {
    Button,
  },
  data() {
    return {
      owners: [],
      selected_owner_ssn: "",
      updateOwner: [],
      success_message: "",
    };
  },
  created() {
    OwnerService.getAllowners().then((response) => {
      this.owners = response.data;
      console.log(this.owners);
    });
  },
  methods: {
    selectedOwner() {
      this.updateOwner = this.owners.find(
        (owners) => owners.ssn === this.selected_owner_ssn
      );
      return null;
    },
    UpdateOwner() {
      OwnerService.update(this.updateOwner)
        .then((response) => {
          console.log(response.data);
          this.updateOwner = [];
          this.success_message = "UPDATED OWNER SUCCESSFULLY";
        })
        .catch((error) => {
          console.error(error);
          this.success_message = "ERROR : "+error;
        });
    },
  },
};
</script>
