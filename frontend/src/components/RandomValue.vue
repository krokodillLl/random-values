<template>
  <div>
    <div class="d-flex justify-content-center">
      <div class="form-outline me-3">
        <select id="form1" class="btn btn-primary dropdown-toggle form-control" aria-expanded="false"
                v-model="selectedCategory">
          <option class="dropdown-item" v-for="category in categories" :key="category.id" :value="category.id">
            {{ category.name }}
          </option>
        </select>
        <label class="form-label" for="form1">Category</label>
      </div>
      <button :disabled="!selectedCategory" class="btn btn-primary" @click="getRandomValue">Get Random Value</button>
    </div>
    <div>
      <div class="container" v-if="randomValue">
        <div class="row p-3 border">
          <div class="col">
            <h3>Name</h3>
          </div>
          <div class="col">
            <h3>Description</h3>
          </div>
          <div class="col col-md-3">
            <h3>Picture</h3>
          </div>
        </div>
        <div class="row p-3 border">
          <div class="col">
            <h3>{{ randomValue.name }}</h3>
          </div>
          <div class="col">
            <h3>{{ randomValue.description }}</h3>
          </div>
          <div class="col col-md-3">
            <img :src="randomValue.picture" alt="image" class="image-fit"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import apiClient from '../services/api';

export default {
  props: {
    categories: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      randomValue: null,
      selectedCategory: null
    };
  },
  methods: {
    getRandomValue() {
      apiClient.get(`/api/1/random/value/by/${this.selectedCategory}`)
          .then(response => {
            this.randomValue = response.data;
          })
          .catch(error => {
            console.error('Error fetching random value:', error);
          });
    }
  }
};
</script>

<style>
.image-fit{
  height: 100%;
  width: 100%;
  object-fit: cover;
}
</style>