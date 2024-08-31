# 🤝 Contributing

We're excited that you're interested in contributing to this project! Before submitting your contribution, please read through the following guide.

## 🔧 Setup

This project's documentation uses [Yarn](https://yarnpkg.com/) for package management. Please make sure you have it installed before proceeding.

1. Fork the repo and create your branch from `main`.
2. Run `yarn install` in the repository root.
3. Run `yarn run docs:dev` to start the development server.
4. Click "Install" on the popup to install the development script.
5. Make your changes!

### Backend Setup

This project's backend can be managed with either [Maven](https://maven.apache.org/) or [Gradle](https://gradle.org/). Please ensure you have one of these tools installed before proceeding.

#### Maven

1. Fork the repo and create your branch from `main`.
2. Run `mvn compile` in the repository root.
3. Enjoy it!

#### Gradle

1. Fork the repo and create your branch from `main`.
2. Run `./gradlew build` in the repository root.
3. Enjoy it!

## ✅ Linting and Type Checking

This project uses [ESLint](https://eslint.org/) and [TypeScript](https://www.typescriptlang.org/) for linting and type checking, respectively. Please make sure your code passes both before submitting a PR.

```bash
yarn run lint
yarn run test
```

## 📝 Commit Message Format

This project follows the [Conventional Commits](https://www.conventionalcommits.org/) specification. Please make sure your commit messages are formatted correctly.

**Please mention the issue number in the commit message or the PR description.**

