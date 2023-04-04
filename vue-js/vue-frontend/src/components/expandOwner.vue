<template>
  <div>
    <h3>Home ID {{ data.homeId }}</h3>
    <p><b>Owner Details</b></p>
    <p>
      <b> {{ owner.firstName }} {{ owner.lastName }}</b>
    </p>
    <p>Dependents : {{ owner.numDependents }}</p>
    <p>Anuual Income : {{ owner.annualIncome }}</p>
    <p>Date of Birth : {{ owner.dateOfBirth }}</p>
    <p>Land size : {{ owner.profession }}</p>
    <p>Phone : {{ owner.phone }}</p>
    <p>Email : {{ owner.email }}</p>
  </div>
</template>

<script>
import HomeService from "@/services/HomeService";
export default {
  props: {
    data: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      owner: [],
    };
  },
  created() {
    HomeService.getOwnerofhome(this.data.ownerId).then((response) => {
      this.owner = response.data;
      console.log(this.owner);
    });
  },
};
</script>
