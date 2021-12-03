FROM ruby:2.4.9
ENV BUNDLER_VERSION=1.17.3
RUN apt-get update -qq && apt-get install -y nodejs postgresql-client
RUN gem install bundler -v 1.17.3
WORKDIR /app
COPY Gemfile Gamefile.lock ./
RUN bundle check || bundle install
COPY . ./

ENTRYPOINT ["./entrypoints/docker-entrypoint.sh"]