<template>
    <div class="wrapper">
        <div
            class="menu"
            :class="{ selected: this.selected == 'home' }"
            @click="$router.push({ name: 'Home' })"
        >
            <div class="menu-icon">
                <i class="fas fa-home"></i>
            </div>
            <span class="menu-name title-7">홈</span>
        </div>
        <div
            class="menu"
            :class="{ selected: this.selected == 'category' }"
            @click="$router.push({ name: 'Category' })"
        >
            <div class="menu-icon">
                <i class="fas fa-bars"></i>
            </div>
            <span class="menu-name title-7">카테고리</span>
        </div>
        <div
            class="menu"
            :class="{ selected: this.selected == 'ranking' }"
            @click="$router.push({ name: 'Ranking' })"
        >
            <div class="menu-icon">
                <i class="fas fa-medal"></i>
            </div>
            <span class="menu-name title-7">랭킹</span>
        </div>
        <div
            class="menu"
            :class="{ selected: this.selected == 'diary' }"
            @click="$router.push({ name: 'Diary' })"
        >
            <div class="menu-icon">
                <i class="fas fa-edit"></i>
            </div>
            <span class="menu-name title-7">일기</span>
        </div>
        <div
            class="menu"
            :class="{ selected: this.selected == 'mypage' }"
            @click="$router.push({ name: 'MyPage' })"
        >
            <div class="menu-icon">
                <i class="fas fa-user"></i>
            </div>
            <span class="menu-name title-7">마이페이지</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "Template",
    components: {},
    // props
    props: {
        selected: String,
    },
    // data
    data() {
        return {};
    },
    // computed
    computed: {},
    // lifecycle hook
    mounted() {},
    // methods
    methods: {},
};
</script>

<style scoped src="./MyFooter.css"></style>
