<template>
  <div class="location">
    <span v-if="this.name.length >= 11" class="title-7 location-title">
      {{ this.name }}
    </span>
    <span v-else class="title-6 location-title">{{ this.name }}</span>
  </div>
</template>
<script>
export default {
  name: "LocationTag",
  props: {
    name: String,
  },
};
</script>
<style scoped src="./LocationTag.css"></style>
